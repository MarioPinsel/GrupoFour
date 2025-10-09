#!/usr/bin/python3
import os, sys, sqlite3, hashlib
from deco import decode_base64

print("Content-Type: text/html\n")

# --- Obtener el parámetro 'name' del query string ---
params = {}
if os.environ.get("REQUEST_METHOD", "") == "GET":
    import urllib.parse
    query = os.environ.get("QUERY_STRING", "")
    params = urllib.parse.parse_qs(query)

name = params.get("name", [""])[0]

if not name:
    print("<h3>No ingresaste un nombre.</h3>")
    sys.exit(0)

try:
    # --- Conexión a la base de datos ---
    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()
    cur.execute("SELECT name, sha256, imagen64 FROM image WHERE name = ?", (name,))
    row = cur.fetchone()
    conn.close()

    if row:
        nombre, sha256_val, imagen64 = row

        # --- 1. Validar Base64 ---
        try:
            data = decode_base64(imagen64)
            if not data or len(data) == 0:
                raise ValueError("Base64 vacío")
        except Exception:
            print("<h3>⚠️ La imagen está dañada o no se puede mostrar (error de codificación Base64).</h3>")
            sys.exit(0)

        # --- 2. Verificar hash SHA256 ---
        hash_actual = hashlib.sha256(data).hexdigest()
        if hash_actual != sha256_val:
            print("<h3>⚠️ La imagen fue modificada o el hash SHA256 no coincide.</h3>")
            sys.exit(0)

        # --- 3. Mostrar datos si todo está bien ---
        print(f"<h3>Nombre: {nombre}</h3>")
        print(f"<p><b>SHA256:</b> {sha256_val}</p>")
        print("<p><b>Base64:</b></p>")
        print(f'<textarea readonly>{imagen64}</textarea><br>')
        print(f'<img src="data:image/png;base64,{imagen64}" style="max-width:300px;"/>')

    else:
        print("<h3>No se encontró la imagen.</h3>")

except Exception as e:
    print(f"<h3>Error en search.py: {e}</h3>")

