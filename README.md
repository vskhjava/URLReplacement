# URLReplacement
Преобразование URL для "черного" списка сетевого экрана
Удаляет из строки адреса протоколы (http, https), заменяет [.] на .
Удаляется путь к ресурсу, оставляется имя хоста.
Удаляет [:] и все, что следует за этой комбинацией

Пример 1.
было: xx[.]xxx[.]xx[.]x
стало: xx.xxx.xx.x

Пример 2.
было: hxxp[:]//xxxxx.xx/xx/xxxx/xx/releases/latest/download/cloudflar/index[.]php
стало: xxxxx.xx

Пример 3.
было:  xxx[.]xxx[.]xxx[.]xxx[:]xxxx
стало: xxx.xxx.xxx.xxx
