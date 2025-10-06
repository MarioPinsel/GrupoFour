#!/usr/bin/python3
import sqlite3, json

print("Content-Type: application/json\n")

try:
    conn = sqlite3.connect("/var/www/html/proyecto/dataBase.db")
    cur = conn.cursor()
    cur.execute("SELECT name FROM image ORDER BY id DESC")
    rows = cur.fetchall()
    conn.close()

    names = [r[0] for r in rows]
    print(json.dumps(names))

except Exception as e:
    print(json.dumps({"error": str(e)}))
