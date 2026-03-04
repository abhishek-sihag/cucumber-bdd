Feature: Create Product API

  @api
  Scenario Outline: Validate product creation with multiple inputs
    Given User has request specification
    When User sends POST request with id "<id>" title "<title>" price "<price>" description "<description>" category "<category>" image "<image>"
    Then Validate API response

    Examples:
      | id | title        | price  | description           | category    | image                      |
      | 0  | Ram          | 0.1    | Not valid entry       | Kendra      | https://img1.com           |
      | 1  | Phone        | 999.9  | Flagship phone        | Electronics | https://img2.com           |
      | 2  | Laptop       | 1500.5 | Gaming laptop         | Electronics | https://img3.com           |
      | 3  | Book         | 10.75  | Educational book      | Study       | https://img4.com           |
      | 4  | Shoes        | 59.99  | Running shoes         | Fashion     | https://img5.com           |
      | 5  | Watch        | 199.99 | Smart watch           | Accessories | https://img6.com           |
      | 6  | Bag          | 45.50  | Travel backpack       | Travel      | https://img7.com           |
      | 7  | Chair        | 120.00 | Office chair          | Furniture   | https://img8.com           |
      | 8  | Headphones   | 89.90  | Noise cancelling      | Electronics | https://img9.com           |
      | 9  | Bottle       | 15.25  | Water bottle          | Kitchen     | https://img10.com          |