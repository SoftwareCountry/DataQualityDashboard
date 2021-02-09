FROM r-base

RUN apt-get update && apt-get install -y \
	libcurl4-openssl-dev \
	libssl-dev \
	libcairo-dev \
	default-jdk \
	libxml2-dev

RUN R -e "install.packages('DatabaseConnector')"
RUN R -e "install.packages('SqlRender')"
RUN R -e "install.packages('ParallelLogger')"
RUN R -e "install.packages('websocket')"
RUN R -e "install.packages('devtools')"
RUN R -e "devtools::install_github('OHDSI/DataQualityDashboard')"
RUN R -e "install.packages('Rserve',, 'http://rforge.net/', type='source')"

EXPOSE 6311

VOLUME /rserve

WORKDIR .

RUN mkdir root/R

COPY execution.R root/R/execution.R
COPY messageSender.R root/R/messageSender.R
COPY rServer.R root/R/rServer.R

ENTRYPOINT R -e "Rserve::run.Rserve(remote=TRUE)"