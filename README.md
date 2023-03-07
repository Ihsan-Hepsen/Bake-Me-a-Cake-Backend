# BMAC - Backend Finale

### Author: Ihsan Hepsen

## Bounded Contexts
- Store
- Factory
- Warehouse

## Event & Command Catalog

### Event Catalog

| Event                       | Description                                                    | Listener                             |
|:----------------------------|:---------------------------------------------------------------|:-------------------------------------|
| `NewOrderPlacedEvent`       | A new sales order has been placed.                             | `NewBakingOrderAdapter`              |
| `IngredientListIsSentEvent` | Ingredient list for baking orders is sent to the Warehouse BC. | `WarehouseOutBoundOrderAMQPReceiver` |

### Command Catalog

| Command                       | Description                                                          |
|:------------------------------|:---------------------------------------------------------------------|
| `PlaceOrderCommand`           | Command to place new sales order.                                    |
| `OutBoundOrderCommand`        | Sent to Warehouse BC to generate outbound orders with ingredients.   |
| `FulfillOutBoundOrderCommand` | Command for fulfilling outbound orders (fulfilling the ingredients). |
| `NewInBoundOrderCommand`      | Command that is used to generate new inbound orders.                 |


## Testing
- To run tests, run `mvn clean test` in the root directory of the project.
