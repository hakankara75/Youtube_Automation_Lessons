@createAssertMethod
Feature:createAssertMethod
  Scenario:createAssertMethod
    Given magento sitesine gidilir
    And women menusu ustune gelinir
    And tops menusu ustune gelinir
    And jackets secilir
    And ilk urunde S beden secilir
    And ilk urunde mavi renk secilir
    And add to cart butonuna basilir
    And sepet tiklanir
    When see details tiklanir
    Then alinan urun dogrulanir
