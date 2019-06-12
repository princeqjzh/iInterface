from flask import Flask, make_response, jsonify

app = Flask(__name__)
bj_code = '101010100'
BJ_W_INFO = {
    "weatherinfo": {
        "city": "北京",
        "cityid": bj_code,
        "temp1": "18℃",
        "temp2": "31℃",
        "weather": "多云转阴",
        "img1": "n1.gif",
        "img2": "d2.gif",
        "ptime": "18:00"
    }
}

sh_code = '101020100'
SH_W_INFO = {
    "weatherinfo": {
        "city": "上海",
        "cityid": sh_code,
        "temp1": "19℃",
        "temp2": "23℃",
        "weather": "大雨转中雨",
        "img1": "n9.gif",
        "img2": "d8.gif",
        "ptime": "18:00"
    }
}

sz_code = '101280601'
SZ_W_INFO = {
    "weatherinfo": {
        "city": "深圳",
        "cityid": sz_code,
        "temp1": "24℃",
        "temp2": "30℃",
        "weather": "阵雨转大雨",
        "img1": "n3.gif",
        "img2": "d9.gif",
        "ptime": "18:00"
    }
}


@app.route('/data/cityinfo/' + bj_code + '.html', methods=['GET'])
def get_bj_weather():
    return make_response(jsonify(BJ_W_INFO), 200)


@app.route('/data/cityinfo/' + sh_code + '.html', methods=['GET'])
def get_sh_weather():
    return make_response(jsonify(SH_W_INFO), 200)


@app.route('/data/cityinfo/' + sz_code + '.html', methods=['GET'])
def get_sz_weather():
    return make_response(jsonify(SZ_W_INFO), 200)


if __name__ == "__main__":
    app.run(port=3000, debug=True, host='0.0.0.0')
