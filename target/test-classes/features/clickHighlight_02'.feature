@kitap
Feature: US01 Cok satan edebiyat kitapları

  Scenario: TC01 cok satan edebiyat kitaplari sayfasi testi
    Given Kullanici "kitapYurduUrl" e gider
    When kullanici cok satan kitaplar menusu ustune gelir
    And 3 saniye bekleme yapar
    And cok satan edebiyat kitaplari linkini tiklar