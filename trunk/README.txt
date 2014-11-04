Projekt lze spustit jednoduše pomocí "ant run"
projekt se zkompiluje a zavolá se hotová aplikace (class fily jsou v adresáři testdata). Aplikaci se jako parametr předává nejdříve cesta k classfile s main třídou a poté další cesty k ostatním classfileům.

Aplikace má implementovány pouze instrukce a funkce potřebné pro běh naší konkrétní implementace problému batohu. Zdrojový kód této aplikace je k nahlédnutí v adresáři test_source.

Požadavky implementace javy jsem se učil za chodu, proto je struktura projektu dost zmatená a sám se v ním přestávám vyznávat. Kdybych práci psal dnes znovu od začátku, navrhl bych spoustu věcí jinak, aby některé funkcionality byly snáze implementovatelné. V aktuálním provedení je například velmi obtížné implementovat GC.
