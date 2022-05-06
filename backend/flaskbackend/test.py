from flask import Flask
from flask import request
import flask
# import style_transfer
import base64
import os

def decode_base64(data):
    """Decode base64, padding being optional.
    :param data: Base64 data as an ASCII byte string
    :returns: The decoded byte string.

    """
    missing_padding = 4 - len(data) % 4
    if missing_padding:
        data += '='* missing_padding
    return base64.b64decode(data)


file = open('file_base64.txt', 'r')

imgBase64 = file.read()
# imgBase64.replace('/', '_')
img = decode_base64(imgBase64)
print(img)

filename = 'some_image.jpg'  # I assume you have a way of picking unique filenames
with open(filename, 'wb') as f:
    f.write(img)