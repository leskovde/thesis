1. Je potreba udelat nejprve kostru pro G1, G2, G3
		- az pak je mozne poradne resit G3
		- naive implementace celeho debugovani
2. Multithreading se vynecha ze zadani, ale mam na nej myslet
		- motivace jsou velke programy, ty typicky nejsou single threaded
		- pokud se najde zpusob, jak to udelat multithreaded snadno, pak bych to mel zkusit

- Cil je prozkoumat (zkusit), ne slibit funkcni implementaci
	- Vysledek je prototyp, nad kterym jde experimentovat
- Vstup:
	- velka aplikace (kus kodu)
	- vypis procedur (v nejakem souboru - udelat co nejjednoduseji)
- Udelat 5 kroku
		- oznacit procedury
		- program se pak pusti tak, aby se procedury zavovaly - sesbirat logy a vysledky analyzy
		- vygenerovat testy
		- spustit testy s ruznymi vstupy, najit vadne vstupy
- Mirit na Javu
	- puvodni motivace - v Mante bezi analyzy moc dlouho, chce je zkoumat v urcitych castech (procedurach)
	- cilit designem na to, aby to slo rozsirit na dalsi jazyky
		- modularni
		- udelat nejake standardni formaty, jak se data predavaji
		- (motivace - vypada to, ze kroky jde udelat obecne nad jakymkoliv jazykem)
	- vybrat nejake knihovny pro analyzu kodu/bytecodu
		- (ASM, RoadRunner, DiSL, JVM TI)
		- sber dat za runtime
		- nemely by mit velky vliv na performance
- Meetingy v pondeli kolem ctvrte kazdych 14 dni
- popis debugovani může být kratší - stačí jen to, že je to manuální a zdlouhavé
- Dopsat, že je důležitý performance
- zbytek super


- je dulezite pri vybirani dynamic analysis frameworku davat pozor na performance
	- treba JVM TI hodne brzdi performance kdyz se zaznamenavaji vsechny volani funkci
- dynamicka analyza = instrumentovani a vytahovani informaci, ktere me zajimaji
- Tuma na tohle ma prednasku
- text prace ma byt ve skolnim gitlabu, zbytek muze byt na githubu

- nejspíš bude potřeba použít nějaký framework s dostatečnou abstrakcí (DISL), jelikož u low level instrumentace budu narážet na věci z JVM, které neznám 
- JVM TI umožňuje ovládat exekuci, nejspíš tak dosáhnu lepšího coverage (pokud algoritmicky ovlivním běh, například změním data před podmínkou)
- RoadRunner podporuje concurrency, dobrý na prototyping (tools napsané pomocí něj mají jen pár desítek řádků) => dobrá abstrakce
- RoadRunner je v pořádku pro větší programy, ale je potřeba porovnat performance se zbytkem
- ASM má nějakou úroveň abstrakce, je ale především zaměřený na performance
- ASM se používá ve velkých projektech a compilerech 
- Pro jakýkoliv framework bych měl mít wrapper kvůli modularitě s jinými jazyky