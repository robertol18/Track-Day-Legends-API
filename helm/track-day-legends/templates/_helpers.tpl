{{/*
Expand the name of the chart.
*/}}
{{- define "track-day-legends.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "track-day-legends.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Create chart label.
*/}}
{{- define "track-day-legends.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels.
*/}}
{{- define "track-day-legends.labels" -}}
helm.sh/chart: {{ include "track-day-legends.chart" . }}
{{ include "track-day-legends.selectorLabels" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/*
Selector labels.
*/}}
{{- define "track-day-legends.selectorLabels" -}}
app.kubernetes.io/name: {{ include "track-day-legends.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/*
Resolve secret name used by Deployment.
*/}}
{{- define "track-day-legends.secretName" -}}
{{- if .Values.secret.existingSecret -}}
{{- .Values.secret.existingSecret -}}
{{- else if .Values.secret.create -}}
{{- printf "%s-secret" (include "track-day-legends.fullname" .) -}}
{{- else -}}
{{- "" -}}
{{- end -}}
{{- end -}}
