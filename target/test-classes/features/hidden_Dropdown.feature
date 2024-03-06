@hiddenDropdown
  Feature: hiddenDropdown
    Scenario: hiddenDropdown_1
      Given gratis sitesine gidilir
      When searchboxa "Kantaron" girilir
      Then Listenin ilk sirasinda "Dp Şampuan Kantaron" gorundugu dogrulanir

    Scenario: hiddenDropdown_2
      Given demoqa sitesine gidilir
      When State and City den "NCR" secilir
      Then "NCR" secildigi dogrulanir