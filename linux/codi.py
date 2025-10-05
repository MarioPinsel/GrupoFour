#!/usr/bin/python3

imagen = "rauw.jpeg"
base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

with open(imagen, "rb") as f:
    imagen_bytes = f.read()

rta = ""

for i in range(0, len(imagen_bytes), 3):
    paso = imagen_bytes[i:i+3]
    padding = 3 - len(paso)
    paso += b'\x00' * padding
    num = (paso[0] << 16) + (paso[1] << 8) + paso[2]
    
    grupos = [
        (num >> 18) & 0x3F,
        (num >> 12) & 0x3F,
        (num >> 6) & 0x3F,
        num & 0x3F
    ]

    chars = [base64[j] for j in grupos]

    for j in range(1, padding + 1):
        chars[-j] = '='

    rta += ''.join(chars)

print(rta)
