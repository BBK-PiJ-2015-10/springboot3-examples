package tr.springcloudstreamacaprocessor.domain;

import java.util.UUID;

public record CardHolderData(UUID userId, String name, String address) {
}
