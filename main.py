print("bot start")
import datetime
import requests

dziś = datetime.date.today()
d1 = dziś.strftime("%Y%m%d")
data = d1[2:]

url = f'http://www.lo4.poznan.pl/zast/z2.php?plik=http://swojska.lo4.poznan.pl/zast/{data}.xls'
zast = requests.get(url, allow_redirects=True)
print("pobrano:",url)
# open('plan.html', "wb").write(r.content)
