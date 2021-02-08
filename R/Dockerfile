FROM r-base

RUN echo 'install.packages(c(\
        "ParallelLogger", \
        "SqlRender", \
        "DatabaseConnector", \
        "webscoket", \
        "Rserve"), \
        repos="https://cran.rstudio.com", \
        dependencies=TRUE \
    )' > /tmp/packages.R \
    && Rscript /tmp/packages.R

#RUN install2.r --error \
#    -r "https://cran.rstudio.com" \
#    devtools \
#    Rserve && \
#    Rscript -e "devtools::install_github('OHDSI/DataQualityDashboard')" && \
#    rm -rf /tmp/downloaded_packages/ /tmp/*.rds

#RUN install2.r --error \
#    -r "https://cran.rstudio.com" \
#    rJava \
#    xml2 \
#    rm -rf /tmp/downloaded_packages/ /tmp/*.rds
#
#RUN install2.r --error \
#    -r "https://cran.rstudio.com" \
#    DatabaseConnectorJars \
#    SqlRender \
#    ParallelLogger \
#    rm -rf /tmp/downloaded_packages/ /tmp/*.rds
#
#RUN install2.r --error \
#    -r "https://cran.rstudio.com" \
#    DatabaseConnector \
#    webscoket \
#    Rserve && \
#    rm -rf /tmp/downloaded_packages/ /tmp/*.rds

# Expose port 6311
EXPOSE 6311
ENTRYPOINT R -e "Rserve::run.Rserve(remote=TRUE)"