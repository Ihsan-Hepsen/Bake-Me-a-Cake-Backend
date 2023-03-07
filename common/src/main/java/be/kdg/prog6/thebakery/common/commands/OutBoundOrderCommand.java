package be.kdg.prog6.thebakery.common.commands;

import java.util.Map;
import java.util.UUID;

public record OutBoundOrderCommand(Map<UUID, Double> ingredientList) {
}
