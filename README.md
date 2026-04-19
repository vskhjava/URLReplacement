# URLReplacement
Преобразование URL для "черного" списка сетевого экрана
Удаляет из строки адреса протоколы (http, https), заменяет [.] на .
Удаляется путь к ресурсу, оставляется имя хоста.

Пример 1.
было: xx[.]xxx[.]xx[.]x
стало: xx.xxx.xx.x

Пример 2.
было: hxxp[:]//xxxxx.xx/xx/xxxx/xx/releases/latest/download/cloudflar/index[.]php
стало: xxxxx.xx
