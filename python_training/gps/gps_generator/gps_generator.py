#!C:\Python34\python.exe
"""Generate file with GPS points

"""
import random
import time
import math


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
    point = []
    id = 0
    for i in start_point:
        point.append(i)
    print(id, point)

    while amount > 0:
        point = get_next_point(point, step)
        amount -= 1
        id += 1
        print(id, point)
    return


def main():
    start_point = [100, 100, 1]
    generate(start_point, 5, 100)
    return


# CLI execution
if __name__ == "__main__":
    main()
