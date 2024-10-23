# Use the official Python image
FROM python:3.9

RUN apt-get update

RUN apt-get upgrade -y

RUN apt-get install -y \
    libhdf5-dev \
    libhdf5-serial-dev \
    build-essential

RUN rm -rf /var/lib/apt/lists/*

RUN pip install webdriver-manager

RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb

RUN cd /etc/apt
RUN ls -la
RUN apt-get update

RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
RUN apt-get update && apt-get install -y google-chrome-stable

ENV DISPLAY=:99
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROMEDRIVER=/usr/local/bin/chromedriver

# Set the working directory
WORKDIR /app

# Copy requirements.txt and install dependencies
COPY requirements.txt ./

RUN pip install --no-cache-dir -r requirements.txt

# Copy the application code
COPY webTest.py ./

# Run the application
CMD ["python", "webTest.py"]
