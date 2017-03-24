## 网络相关笔记

### OSI七层模型

<table class="table">
  <tbody>
  <tr>
    <th colspan="5">OSI 模型</th>
  </tr>
  <tr>
    <th></th>
    <th>数据单元</th>
    <th>层</th>
    <th>功能</th>
  </tr>
  <tr>
    <th rowspan="4">主机层</th>
    <td rowspan="3">Data（数据）</td>
    <td>7. 应用层</td>
    <td>
      <small>网络进程到应用程序。</small>
      针对特定应用规定各层协议、时序、表示等，进行封装 。在端系统中用软件来实现，如HTTP等
    </td>
  </tr>
  <tr>
    <td>6. 表示层</td>
    <td>
      <small>数据表示形式，加密和解密，把机器相关的数据转换成独立于机器的数据。</small>
      规定数据的格式化表示 ，数据格式的转换等
    </td>
  </tr>
  <tr>
    <td>5. 会话层</td>
    <td>
      <small>主机间通讯，管理应用程序之间的会话。</small>
      规定通信时序 ；数据交换的定界、同步，创建检查点等
    </td>
  </tr>
  <tr>
    <td>Segments（数据段）</td>
    <td>4. 传输层</td>
    <td>
      <small>在网络的各个节点之间可靠地分发数据包。</small>
      所有传输遗留问题；复用；流量；可靠
    </td>
  </tr>
  <tr>
    <th rowspan="3">媒介层</th>
    <td>网络分组/数据报文</td>
    <td>3. 网络层</td>
    <td>
      <small>在网络的各个节点之间进行地址分配、路由和（不一定可靠的）分发报文。</small>
      路由（ IP寻址）；拥塞控制。
    </td>
  </tr>
  <tr>
    <td>Bit/Frame（数据帧）</td>
    <td>2. 数据链路层</td>
    <td>
      <small>一个可靠的点对点数据直链。</small>
      检错与纠错（CRC码）；多路访问；寻址
    </td>
  </tr>
  <tr>
    <td>Bit（比特）</td>
    <td>1. 物理层</td>
    <td>
      <small>一个（不一定可靠的）点对点数据直链。</small>
      定义机械特性；电气特性；功能特性；规程特性
    </td>
  </tr>
  </tbody>
</table>