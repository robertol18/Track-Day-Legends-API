{{- define "observability.mimir.name" -}}
{{- default "mimir" .Values.mimir.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "observability.mimir.fullname" -}}
{{- if .Values.mimir.fullnameOverride -}}
{{- .Values.mimir.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- include "observability.mimir.name" . -}}
{{- end -}}
{{- end -}}
