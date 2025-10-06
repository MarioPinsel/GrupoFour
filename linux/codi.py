BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

def encode_base64(data: bytes) -> str:
    rta = ""
    for i in range(0, len(data), 3):
        paso = data[i:i+3]
        padding = 3 - len(paso)
        paso += b'\x00' * padding
        num = (paso[0] << 16) + (paso[1] << 8) + paso[2]

        grupos = [
            (num >> 18) & 0x3F,
            (num >> 12) & 0x3F,
            (num >> 6) & 0x3F,
            num & 0x3F
        ]

        chars = [BASE64_CHARS[j] for j in grupos]

        for j in range(1, padding + 1):
            chars[-j] = '='

        rta += ''.join(chars)
    return rta
