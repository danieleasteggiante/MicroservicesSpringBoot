#!/bin/bash

result = $(ollama list | grep tinyllama)

if [ -z "$result" ]; then
  ollama pull tinyllama
fi