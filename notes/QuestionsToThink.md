### 请求重发问题
#### 1. 由于网络波动，服务端同时收到了客户端的第一次请求和稍后的重试请求，这里如何排重
#### 2. 由于网络波动，客户端向服务端的请求没有在限定时间内响应，如果为关键操作，这个请求时不应该重发的，这时应作何处理