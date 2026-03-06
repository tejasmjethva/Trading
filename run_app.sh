#!/bin/bash

# Navigate to the script's directory
cd "$(dirname "$0")"

# Check if virtual environment exists, if not create it
if [ ! -d "venv" ]; then
    echo "Creating virtual environment..."
    python3 -m venv venv
fi

# Activate virtual environment
source venv/bin/activate

# Install requirements (quietly to save time)
pip install -q -r requirements.txt

# Run the streamlit app
echo "Launching Trading Calculator..."
python -m streamlit run app.py --server.headless true
