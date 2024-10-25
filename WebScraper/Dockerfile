# Use the official Python image
FROM infologistix/docker-selenium-python:alpine

# Set the working directory
WORKDIR /app

# Copy requirements.txt and install dependencies
COPY requirements.txt ./

RUN pip install --no-cache-dir -r requirements.txt

# Copy the application code
COPY webTest.py ./

# Run the application
CMD ["python", "webTest.py"]
