# Projekt nr 1
[Opis projektu](https://github.com/apohllo/obiektowe-lab/tree/master/proj1)

1. - [x] Aplikacja ma być realizowana z użyciem graficznego interfejsu użytkownika z wykorzystaniem biblioteki JavaFX.

1. Jej głównym zadaniem jest umożliwienie uruchamiania symulacji o wybranych konfiguracjach.
	- [x] Powinna umożliwić wybranie jednej z uprzednio przygotowanych gotowych konfiguracji.

	- [x] Dostępne konfiguracje mają formę znajdujących się w odpowiednim folderze na dysku plików konfiguracyjnych (plik może zawierać po prostu pary klucz + wartość, po jednej na linijkę).

	- [x] Istnieje możliwość wczytania alternatywnej, sporządzonej przez użytkownika konfiguracji.

1. - [x] Uruchomienie symulacji powinno skutkować pojawieniem się nowego okna obsługującego daną symulację.

1. - [x] Jednocześnie uruchomionych może być wiele symulacji, każda w swoim oknie, każda na osobnej mapie.

1. - [x] Sekcja symulacji ma wyświetlać animację pokazującą pozycje zwierząt, ich energię w dowolnej formie (np. koloru lub paska zdrowia) oraz pozycje roślin - i ich zmiany.

1. - [x] Program musi umożliwiać zatrzymywanie oraz wznawianie animacji w dowolnym momencie (niezależnie dla każdej mapy - patrz niżej).

1. Program ma pozwalać na śledzenie następujących statystyk dla aktualnej sytuacji w symulacji:
	- [x] liczby wszystkich zwierząt,
	- [x] liczby wszystkich roślin,
	- [x] liczby wolnych pól,
	- [x] najpopularniejszych genotypów,
	- [x] średniego poziomu energii dla żyjących zwierząt,
	- [x] średniej długości życia zwierząt dla martwych zwierząt (wartość uwzględnia wszystkie nieżyjące zwierzęta - od początku symulacji),

1. - [ ] Jeżeli zdecydowano się na to w momencie uruchamiania symulacji, to jej statystyki powinny być zapisywane (każdego dnia) do pliku CSV. Plik ten powinnien być "otwieralny" przez dowolny rozujmiejący ten format program (np. MS Excel).

1. - [ ] Po zatrzymaniu programu można oznaczyć jedno zwierzę jako wybrane do śledzenia. Od tego momentu (do zatrzymania śledzenia) UI powinien przekazywać nam informacje o jego statusie i historii:
	- [ ] jaki ma genom,
	- [ ] która jego część jest aktywowana,
	- [ ] ile ma energii,
	- [ ] ile zjadł roślin,
	- [ ] ile posiada dzieci,
	- [ ] ile dni już żyje (jeżeli żyje),
	- [ ] którego dnia zmarło (jeżeli żywot już skończyło).

1. [ ] Po zatrzymaniu programu powinno być też możliwe pokazanie, które ze zwierząt mają dominujący (najpopularniejszy) genotyp (np. poprzez wyróżnienie ich wizualnie).

1. [x] Aplikacja powinna być możliwa do zbudowania i uruchomienia z wykorzystaniem Gradle'a.
