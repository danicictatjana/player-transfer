INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO klub (id, naziv, budzet) VALUES (1, 'Juventus', 10000000.00);
INSERT INTO klub (id, naziv, budzet) VALUES (2, 'Crvena Zvezda', 5000000.00);
INSERT INTO klub (id, naziv, budzet) VALUES (3, 'Partizan', 200000.00);

INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (1, 'Cristiano Ronaldo', 'napadac', 7, '1987-03-20', false, 1);
INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (2, 'Zoran Popovic', 'golman', 1, '1985-03-20', true, 2);
INSERT INTO igrac (id, ime_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) VALUES (3, 'Filip Kljajic', 'golman', 12, '1987-03-20', false, 3);

INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (1, 1, 2, 200000.00);
INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (2, 2, 1, 3000000.00);
INSERT INTO transfer (id, igrac_id, klub_id, cena) VALUES (3, 3, 1, 500000.00);	