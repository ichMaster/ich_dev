#!/home/ich/anaconda3/bin/python

import psycopg2 as pg
import redis as rd

FILE_LOCATION = '/home/ich/ich_dev/gps_loader/data/20081023025304.plt'
PG_CONNECTION_STRING = "host='localhost' dbname='gpx' user='postgres' password='postgres'"

f = open(FILE_LOCATION, 'r')
connection = pg.connect(PG_CONNECTION_STRING)
cursor = connection.cursor()
rdb = rd.StrictRedis(host='localhost', port='6379', db=0)
json_all = []
geojson_all = []

row_id = 0
for row in f:
    row_id += 1
    if row_id > 6:
        print(row)
        elements = row.strip().split(',')
        user_id, lat, lon, date, time = row_id, elements[0], elements[1], elements[5], elements[6]
        insert_sting = "insert into gps (user_id, gps_date, gps_time, lat, lon) values({0}, '{1}', '{2}', {3}, {4})".format(user_id, date, time, lat, lon)
        json_string = '{{"user_id":"{0}", "lat":"{1}", "lng":"{2}", "date":"{3}", "time":"{4}"}}'.format(user_id, lat, lon, date, time)
        geojson_string = '{{"type": "Feature", "geometry": {{"type": "Point", "coordinates": [{0}, {1}]}}, "properties": {{"user_id": "{2}", "date":"{3}", "time":"{4}"}} }}'.format(lat, lon, user_id, date, time)
        json_all.append(json_string)
        geojson_all.append(geojson_string)
        cursor.execute(insert_sting)
        rdb.set(row_id, json_string)

f.close()
connection.commit()
rdb.set('all_points', '[' + ','.join(json_all) + '{}]')
rdb.set('all_geopoints', '{{"type": "FeatureCollection",  "features": [' + ','.join(json_all) + ']}}')

cursor.execute("select user_id, lat, lon from gps")
lines = cursor.fetchall()
for line in lines:
    print(line)

keys = rdb.keys()
for key in keys:
    val = rdb.get(key)
    print(val)