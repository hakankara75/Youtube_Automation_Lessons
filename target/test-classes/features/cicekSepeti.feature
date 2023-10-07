@dataTable
  Feature: Data Table Test
    Scenario: Cicek Sepeti urun arama
      Given "cicek sepeti" sitesine gidilir
      When hediye linki tiklanir
      And dogum gunu linki tiklanir
      Then dogum gunu sayfasinda oldugu dogrulanir
      When renk drop down tiklanir
      Then sirasiyla renkler secilir
        | renkler    |
        | Bej        |
        | Beyaz      |
        | Çok Renkli |
        | Gri        |
        | Kahverengi |



