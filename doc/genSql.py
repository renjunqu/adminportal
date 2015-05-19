#!/usr/bin/python
# -*- coding: utf-8 -*-
import random
import string
count=1
maxLatitude=39.951168
minLatitude=39.846635
maxLongitude=116.46349
minLongitude=116.344872


print "use joymove;"
while count < 100:
    count = count+1
    latitude   = random.uniform(minLatitude,maxLatitude)
    longitude  = random.uniform(minLongitude,maxLongitude)
    firstChar  = random.choice(string.ascii_uppercase)
    lastChar   = ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(5))
    resultStr="粤"+firstChar+lastChar
    print "insert into JOY_Car(positionX,positionY,desp,state) values( %f, %f,\'%s\',0);" % (longitude,latitude,resultStr)