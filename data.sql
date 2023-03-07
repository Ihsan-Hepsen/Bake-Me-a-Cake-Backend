INSERT INTO
    recipe(recipe_uuid, name)
VALUES
    ('2e4712df-2e15-46da-b45b-cc986a9ef781', 'DONUT_REGULAR'),
    ('e7f2543a-342a-4039-a549-3454a6c73f69', 'DONUT_CHOCOLATE'),
    ('ddf5a27b-3fa6-4c69-904a-2f68c8b913fb', 'DONUT_JELLY'),
    ('04a07048-9c2f-4f47-b7b5-fe839d42844a', 'BAGEL_REGULAR'),
    ('05ce45d9-2880-4557-a746-a753ceea3378', 'BAGEL_BLUEBERRY');


INSERT INTO
    ingredient(ingredient_uuid, amount, type, unit)
VALUES
    ('33b1e654-8daa-496a-9388-2a44b6962228', 5000.00, 'COCOA_POWDER', 'GRAMS'),
    ('d56c473b-7f80-496b-b0eb-ee785e72a8e9', 150.00, 'SUGAR', 'GRAMS'),
    ('5077c8b3-6046-45df-a30c-69da46d2e294', 50.25, 'MILK', 'LITERS'),
    ('27333d48-9770-4a41-975c-d7965c544319', 15.25, 'VANILLA', 'OUNCES'),
    ('eaec3c68-56fa-4f1e-bb50-45a98bd8ac24', 45.5, 'CORN_FLOUR', 'KILOS'),
    ('9a2d019e-dca9-4622-b558-e5295c21cbdd', 20.5, 'CHOCOLATE_CHIPS', 'KILOS');


INSERT INTO
    products(product_uuid, product_name, recipe_recipe_uuid)
VALUES
    ('6b026613-450e-4fda-8ea1-7c403e0e9211', 'Regular Donut', '2e4712df-2e15-46da-b45b-cc986a9ef781'),
    ('7d0d7626-78be-459a-b4dd-9a48701088dd', 'Jelly Filled Donut', 'ddf5a27b-3fa6-4c69-904a-2f68c8b913fb');

INSERT INTO
    recipe_ingredients(recipe_jpa_entity_recipe_uuid, ingredients_ingredient_uuid)
VALUES
    ('2e4712df-2e15-46da-b45b-cc986a9ef781', '5077c8b3-6046-45df-a30c-69da46d2e294'),
    ('2e4712df-2e15-46da-b45b-cc986a9ef781', 'd56c473b-7f80-496b-b0eb-ee785e72a8e9'),
    ('2e4712df-2e15-46da-b45b-cc986a9ef781', '27333d48-9770-4a41-975c-d7965c544319');


INSERT INTO
    stock(stock_item_uuid, amount, expiration_date, ingredient_type, unit)
VALUES
    ('33b1e654-8daa-496a-9388-2a44b6962228', 10000, DATE('2023-01-02'), 'COCOA_POWDER', 'GRAMS'),
    ('d56c473b-7f80-496b-b0eb-ee785e72a8e9', 500, DATE('2023-01-25'), 'SUGAR', 'KILOS'),
    ('5077c8b3-6046-45df-a30c-69da46d2e294', 2400, DATE('2022-12-03'), 'MILK', 'LITERS'),
    ('27333d48-9770-4a41-975c-d7965c544319', 500, DATE('2023-01-09'), 'VANILLA', 'OUNCES'),
    ('eaec3c68-56fa-4f1e-bb50-45a98bd8ac24', 103.5, DATE('2023-12-02'), 'CORN_FLOUR', 'KILOS'),
    ('9a2d019e-dca9-4622-b558-e5295c21cbdd', 6525, DATE('2022-12-31'), 'CHOCOLATE_CHIPS', 'KILOS'),
    ('9a2d019e-dcb9-4622-b599-e5295c21caad', 240, DATE('2022-12-05'), 'FLOUR', 'KILOS');


UPDATE stock
SET category = 'FLOUR'
WHERE stock_item_uuid = 'eaec3c68-56fa-4f1e-bb50-45a98bd8ac24';