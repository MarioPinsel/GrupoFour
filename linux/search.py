#!/usr/bin/python3
import os, sys, sqlite3, urllib.parse

print("Content-Type: text/html\n")

params = {}
if os.environ.get("REQUEST_METHOD", "") == "GET":
    query = os.environ.get("QUERY_STRING", "")
    params = urllib.parse.parse_qs(query)

name = params.get("name", [""])[0]

if not name:
    print("<h3>No ingresaste un nombre.</h3>")
    sys.exit(0)

try:
    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()
    cur.execute("SELECT name, sha256, imagen64 FROM image WHERE name = ?", (name,))
    row = cur.fetchone()
    conn.close()

    if row:
        print(f"<h3>Nombre: {row[0]}</h3>")
        print(f"<p><b>SHA256:</b> {row[1]}</p>")
        print("<p><b>Base64 completo:</b></p>")
        print(f'<textarea readonly>{row[2]}</textarea><br>')
        print(f'<img src="data:image/png;base64,{row[2]}" style="max-width:300px;"/>')
    else:
        print("<h3>No se encontró la imagen con ese nombre.</h3>")

except Exception as e:
    print(f"<h3>Error en search.py: {e}</h3>")
