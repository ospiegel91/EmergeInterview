import requests
from random import randint
import threading
import socket


def get_ip():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        # doesn't even have to be reachable
        s.connect(('10.255.255.255', 1))
        IP = s.getsockname()[0]
    except:
        IP = '127.0.0.1'
    finally:
        s.close()
    return IP


def set_interval(func, sec):
    def func_wrapper():
        set_interval(func, sec)
        func()
    t = threading.Timer(sec, func_wrapper)
    t.start()
    return t


MY_IP_ADDRESS = get_ip()
API_ENDPOINT = 'http://127.0.0.1:9000/sound'
ARR_OF_SOUNDS = [{'sound_type': "siren", 'is_continuous': "True"},
                {'sound_type': "baby_cry", 'is_continuous': "True"},
                {'sound_type': "fire", 'is_continuous': "True"},
                {'sound_type': "rain", 'is_continuous': "True"},
                {'sound_type': "conversation", 'is_continuous': "True"},
                {'sound_type': "wind", 'is_continuous': "True"},
                {'sound_type': "alarm", 'is_continuous': "True"},
                {'sound_type': "dog_bark", 'is_continuous': "False"},
                {'sound_type': "thunder", 'is_continuous': "False"},
                {'sound_type': "gun_shot", 'is_continuous': "False"},
                {'sound_type': "door_slam", 'is_continuous': "False"},
                {'sound_type': "laughter", 'is_continuous': "True"}]


def send_sound_to_server():
    try:
        rand_index = randint(0, 11)
        data = {
                'senderIP': MY_IP_ADDRESS,
                'sound': ARR_OF_SOUNDS[rand_index]['sound_type'],
                'is_continuous': ARR_OF_SOUNDS[rand_index]['is_continuous'],
        }
        r = requests.post(url=API_ENDPOINT, json=data)
        pastebin_url = r.text
        print("The pastebin URL is:%s" % pastebin_url)
    except Exception as e:
        print(e)
        print('Something failed when posting sound to server!')


set_interval(send_sound_to_server, 5)






