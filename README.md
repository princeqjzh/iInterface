# iInterface
接口测试用例Sample
1. 测试城市天气状况接口， 
2. 请求天气接口 例如： 北京 http://www.weather.com.cn/data/cityinfo/101010100.html
3. 解析返回JSON值， 实际值 期望值比对
4. 输出测试报告

Mock演示方法
1. 将 src/test/resources/iInterface.properties 复制粘贴到 {user.home}
2. 将 server_addr 设定为mock server的地址
3. 运行接口测试程序，将会请求 mock server 运行得出结果
4. mock server 代码在 py/weather.py， 使用python3运行
