#!/home/ich/anaconda3/bin/python

import psycopg2 as pg
import redis as rd
import os

FILE_LOCATION = '/home/ich/ich_dev/gps_loader/data/1000/20081023025304.plt'
DATA_DIRECTORY = '/home/ich/ich_dev/gps_loader/data'
PG_CONNECTION_STRING = "host='localhost' dbname='gpx' user='postgres' password='postgres'"

connection = pg.connect(PG_CONNECTION_STRING)
cursor = connection.cursor()
rdb = rd.StrictRedis(host='localhost', port='6379', db=0)
json_all = []
geojson_all = []


for root, dirs, files in os.walk(DATA_DIRECTORY, topdown=False):
    for name in files:
        row_id = 0
        f = open(os.path.join(root, name), 'r')
        user_folder = os.path.basename(root)
        print(os.path.join(root, name))
        for row in f:
            row_id += 1
            if row_id > 6:
                elements = row.strip().split(',')
                user_id, lat, lon, date, time = user_folder, elements[0], elements[1], elements[5], elements[6]
                insert_sting = "insert into gps (user_id, gps_date, gps_time, lat, lon) values({0}, '{1}', '{2}', {3}, {4})".format(user_id, date, time, lat, lon)
                json_string = '{{"user_id":"{0}", "lat":"{1}", "lng":"{2}", "date":"{3}", "time":"{4}"}}'.format(user_id, lat, lon, date, time)
                geojson_string = '{{"type": "Feature", "geometry": {{"type": "Point", "coordinates": [{0}, {1}]}}, "properties": {{"user_id": "{2}", "date":"{3}", "time":"{4}", "title":"user_id: {5}"}} }}'.format(lon, lat, user_id, date, time, user_id)
                json_all.append(json_string)
                geojson_all.append(geojson_string)
                cursor.execute(insert_sting)
        f.close()

connection.commit()
rdb.set('all_points', '[' + ','.join(json_all) + ']')
rdb.set('all_geopoints', '{"type": "FeatureCollection",  "features": [' + ','.join(geojson_all) + ']}')


cursor.execute("select user_id, lat, lon from gps")
lines = cursor.fetchall()
for line in lines:
    print(line)

keys = rdb.keys()
for key in keys:
    val = rdb.get(key)
    print(val)



