# Usa l'immagine ufficiale di OpenTelemetry Collector
FROM otel/opentelemetry-collector:latest

# Copia la configurazione personalizzata del Collector nella directory del container
COPY otel-collector-config.yaml /etc/otel-collector-config.yaml

# Imposta il comando per avviare il Collector con la configurazione personalizzata
CMD [ "--config", "/etc/otel-collector-config.yaml" ]