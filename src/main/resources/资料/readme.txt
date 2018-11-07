说明：MyBatis版本 3.4.1
1.这个是我自己总结的关于MyBatis入参参数的一些笔记，可以参考。
如果有什么疑问或者建议，欢迎致电 642815736@qq.com
与我联系，同你笔芯！
云笔记链接地址：
http://note.youdao.com/noteshare?id=925e4672510ce431e6b16b2987eb9fbf&sub=A6BF8E13CDF34983B6828F98B0E4A7DF


2.在MyBatis中，为了更好的预防SQL注入的问题，要注意 $和# 的使用场景。
$--->适用于排序字段或者动态切换表名
#--->适用于Where条件下的查询参数s
附：
$和#的区别
下述内容摘自：
https://blog.csdn.net/u013552450/article/details/72528498/
1. #将传入的数据都当成一个字符串，会对自动传入的数据加一个单引号。如：order by #user_id#，如果传入的值是111,那么解析成sql时的值为order by ‘111', 如果传入的值是id，则解析成的sql为order by 'id'.
2. $将传入的数据直接显示生成在sql中。如：order by $user_id$，如果传入的值是111,那么解析成sql时的值为order by user_id,  如果传入的值是id，则解析成的sql为order by id.
3. #方式能够很大程度防止sql注入。
4.$方式无法防止Sql注入。
5.$方式一般用于传入数据库对象，例如传入表名.
6.一般能用#的就别用$.
总结来说：#{}来接收参数，会自动的为传入的参数加上引号，然后放置到预编译的SQL中，替代占位符。而 ${}则没有预编译的说法，能直接原封不动的被引入到欲执行的sql中。
------>>>
这个是我整理的笔记：
http://note.youdao.com/noteshare?id=47cf923e5d9e8835d1adbc9bf1840746&sub=07E552E97CE64A4BAB1086FAE5339840