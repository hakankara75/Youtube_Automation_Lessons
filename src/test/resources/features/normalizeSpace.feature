@normalize
Feature: Normalize Space
  Scenario: Test 01 Normalize Space
    Given kullanici Amazon sayfasinda
  When kullanici Başa dön yazisina kadar iner
  Then Başa dön yazisina geldigini dogrular
  And sayfayi kapatir
