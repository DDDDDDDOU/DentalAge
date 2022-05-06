import numpy
from flask import Flask
import flask
import base64
import torch
import numpy as np
from PIL import Image
import io
from age_predictor_model import Bottleneck, AgePredictor
from flask_cors import CORS

app = Flask(__name__)
CORS(app, supports_credentials=True)
device = torch.device("cpu")

size = 500
model_path = "./epoch-31-loss-0.0136-val_loss-0.0233.pth"
img = "./abc.jpg"
model = torch.load(model_path,map_location = 'cpu')

avg_mean = 61.99706384
avg_std = 81.05620047435201
age_max = 20.99
age_min = 6.01

age_predictor = AgePredictor(block=Bottleneck, layers=[3, 4, 23, 3], num_classes=1)
age_predictor = age_predictor.to(device)
age_predictor.load_state_dict(model['state_dict'])
age_predictor.eval()

@app.route('/')
def index():
    return '123'

# four dim rgba
def base64_2_np(imgBase64):
    imgBase64 = imgBase64.replace(' ', '+')
    img_bytes = base64.b64decode(imgBase64)
    img_array = np.array(Image.open(io.BytesIO(img_bytes)))

    return img_array

def np_2_base64(img_array):
    img = Image.fromarray(img_array)

    img_bytes = io.BytesIO()
    img.save(img_bytes, format='JPEG') # format: PNG / JPEG
    img_bytes = img_bytes.getvalue()

    img_base64 = base64.b64encode(img_bytes)

    return img_base64





@app.route('/dental_age', methods = ['POST'])
def dental_age():
    data = flask.request.json
    imgBase64 = data.get('img')
    gender = data.get('gender')
    gender = [int(gender)]
    image = base64_2_np(imgBase64)

    image = Image.fromarray(image)

    image = image.resize((500,500), Image.BILINEAR).convert('L')
    image = numpy.asarray(image)
    image = image.astype(np.float64)
    image = np.expand_dims(image, axis=0)

    image -= avg_mean
    image /= avg_std

    image = np.expand_dims(image, axis=0).repeat(4, 0)
    gender = np.expand_dims(gender, axis=0).repeat(4, 0)

    image = torch.from_numpy(image).float()
    gender = torch.from_numpy(gender).float()

    pred = age_predictor(image, gender)
    pred = pred.cpu().detach().numpy()
    pred = pred[0][0]
    pred = pred * (age_max - age_min) + age_min
    
    return str(pred)


if __name__ == '__main__':
    
    app.run(host='0.0.0.0')
