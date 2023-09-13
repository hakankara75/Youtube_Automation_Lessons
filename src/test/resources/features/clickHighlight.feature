Feature: US1001 Kullanici Amazon Sayfadsinda Arama yapar
  @nutella
  Scenario: TC01 kullanici Amazonda Nutella aratir
    Given kullanici Amazon sayfasinda
    Then kullanici Nutella icin arama yapar
    And sonuclarin Nutella icerdigini test eder
    And sayfayi kapatir