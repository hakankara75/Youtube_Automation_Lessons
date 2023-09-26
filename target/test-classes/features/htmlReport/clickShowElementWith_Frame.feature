Feature: US1001 Kullanici Amazon Sayfadsinda Arama yapar
  @Mum
  Scenario: TC01 kullanici Amazonda Sarı Mum aratir
    Given kullanici Amazon sayfasinda
    Then kullanici Sarı Mum icin arama yapar
    And sonuclarin Sarı Mum icerdigini test eder
    And bir urun secer
    And sayfayi kapatir