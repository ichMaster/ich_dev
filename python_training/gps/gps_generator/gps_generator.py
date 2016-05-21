#!C:\Python34\python.exe
"""Generate file with GPS points

"""
import random
import time
import math
import sys

FILE_LOCATION = "./res.csv"
CONFIG_FILE_LOCATION = "./conf.txt"
START_POINT = [100, 100, 1]
STEP = 5
AMOUNT = 100


def get_random(max):
    random.seed(time.time())
    r = random.uniform(0, max)
    return r


def get_next_point(point, distance):
    next_point = []
    ang = get_random(360)
    lat = point[0] + distance * math.cos(ang)
    lon = point[1] + distance * math.sin(ang)
    next_point.append(lat)
    next_point.append(lon)
    next_point.append(point[2])
    return next_point


def generate(start_point, step, amount):
    points = []
    point = []
    id = 0
    for i in start_point:
        point.append(i)
    points.append([id, point])

    while amount > 0:
        point = get_next_point(point, step)
        amount -= 1
        id += 1
        points.append([id, point])
    return points


def write_file(file_location, points):
    f = open(file_location, 'w')
    for i in points:
        row = ""
        row += str(i[0])
        for p in i[1]:
            row += "," + str(p)
        row += "\n"
        f.writelines(row)
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

    if len(sys.argv) > 1:
        file_location = sys.argv[1]
    if len(sys.argv) > 2:
        config_file_location = sys.argv[2]
        params = get_config(config_file_location)
        start_point = []
        start_point.append(float(params[0]))
        start_point.append(float(params[1]))
        start_point.append(float(params[2]))
        step = int(params[3])
    if len(sys.argv) > 3:
        amount = int(sys.argv[3])

    main(file_location, start_point, step, amount)
