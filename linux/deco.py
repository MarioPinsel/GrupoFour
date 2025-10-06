BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

def decode_base64(cadena_b64: str) -> bytes:
    """
    Decodifica una cadena en Base64 usando el mismo algoritmo que implementaste.
    Devuelve los bytes originales.
    """
    cadena_b64 = cadena_b64.strip().replace("\n", "").replace(" ", "")

    resultado = bytearray()

    for i in range(0, len(cadena_b64), 4):
        grupo = cadena_b64[i:i+4]

        padding = grupo.count('=')
        grupo = grupo.rstrip('=')

        num = 0
        for c in grupo:
            valor = BASE64_CHARS.index(c)
            num = (num << 6) + valor

        num = num << (padding * 6)

        byte1 = (num >> 16) & 0xFF
        byte2 = (num >> 8) & 0xFF
        byte3 = num & 0xFF

        resultado.append(byte1)
        if padding < 2:
            resultado.append(byte2)
        if padding < 1:
            resultado.append(byte3)

    return bytes(resultado)

