Реализуйте простой тектовый протокол общения между клиентом и сервером, в камках которого они будут обмениваться строками с командами. 
Команда будет представлять из себя <размер данных><разделитель><данные>. 
Где размер будет представлена числом, разделитель символом ":", а данные произвольной строкой. 
Например "5:hello". Сервер должен проверять корректность размера данных. В случаи корректного размера возвращать в ответ 2:ok, а в случаи не совпадения 3:err

Клиент должен сначала 2 раза отправить корректные данные: 5:hello и 4:cool, а затем некорректные 6:haha. 
Сервер должен корректно проверить все сообщения. 
На первые 2 после проверки размера ответить 2:ок, а на 3 некорректное сообщение убедиться что длина не совпадает и вернуть 3:err.