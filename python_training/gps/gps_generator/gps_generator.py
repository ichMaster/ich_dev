#!C:\Python34\python.exe
"""Generate file with GPS points

"""
import random
import time
import math
import sys

FILE_LOCATION = "./res.csv"
CONFIG_FILE_LOCATION = "./conf.txt"
START_POINT = (100, 100, 1)
STEP = 5
AMOUNT = 100


def get_random(max):
    random.seed(time.time())
    r = random.uniform(0, max)
    return r


def get_next_point(point, distance):
    lat, lon, ele = point
    ang = get_random(360)
    new_lat = lat + distance * math.cos(ang)
    new_lon = lon + distance * math.sin(ang)
    return new_lat, new_lon, ele


def generate(start_point, step, amount):
    point = tuple(start_point)
    points = {0: point}
    for id in range(amount):
        point = get_next_point(point, step)
        points.update({id + 1: point})
    return points


def write_file(file_location, points):
    f = open(file_location, 'w')
    for key, val in points.items():
        lon, lat, ele = val
        f.writelines("{id},{lat},{lon},{ele}\n".format(id = key, lat = lat, lon = lon, ele = ele))
    f.close()
    return


def get_config(file_location):
    f = open(file_location, 'r')
    row = f.readline()
    params = row.split(',')
    f.close()
    return params


def main(file_location, start_point, step, amount):
    if file_location is None:
        file_location = FILE_LOCATION
    if start_point is None:
        start_point = START_POINT
    if step is None:
        step = STEP
    if amount is None:
        amount = AMOUNT

    points = generate(start_point, step, amount)
    write_file(file_location, points)
    return


# CLI execution
if __name__ == "__main__":
    config_file_location = CONFIG_FILE_LOCATION
    file_location = FILE_LOCATION
    start_point = START_POINT
    step = STEP
    amount = AMOUNT

    argv_len = len(sys.argv)
    if argv_len > 1:
        file_location = sys.argv[1]
    if argv_len > 2:
        config_file_location = sys.argv[2]
        params = get_config(config_file_location)
        lon, lat, ele = params[:3]
        start_point = float(lon), float(lat), float(ele)
        step = int(params[3])
    if len(sys.argv) > 3:
        amount = int(sys.argv[3])

    main(file_location, start_point, step, amount)
