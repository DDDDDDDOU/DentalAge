from flask import Flask
import style_transfer
# import os
# os.environ['KMP_DUPLICATE_LIB_OK']='True'
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt
import torchvision
import torchvision.transforms as transforms
import torchvision.models as models
# from gevent import pywsgi


app=Flask(__name__)

@app.route('/test')
def test():
    return 'helloworld'

@app.route('/styleTransfer',methods=['POST'])
def styleTransfer():
    img=flask.request.files['image'].read()
    return style_transfer.style_transfer(img)



device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
model_path = "fast_style_transfer_5_10_800.pth"
trained_transform_net = style_transfer.TransformNet().to(device)
trained_transform_net.load_state_dict(torch.load(model_path))
# server=pywsgi.WSGIServer(('0.0.0.0',5000),app)
# server.serve_forever()
app.run(host='0.0.0.0')
