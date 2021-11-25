# document

# Some documentation REFERENCE

IBM Red book on EJB and Websphere Application Server
[IBM Red book](http://www.redbooks.ibm.com/redbooks/pdfs/sg248076.pdf)

Information on Liberty Server [IIOP Endpoint](https://github.com/OpenLiberty/ci.docker)

https://www.ibm.com/docs/en/was-liberty/base?topic=configuration-orb#iiopEndpoint

https://github.com/OpenLiberty/ci.docker/blob/master/releases/latest/full/helpers/build/configuration_snippets/iiop-ssl-endpoint.xml

it all came from this github.com page on OpenLiberty  Docker image
IIOP_ENDPOINT
Description: Add configuration properties for an IIOP endpoint.
XML Snippet Location: iiop-ssl-endpoint.xml when SSL is enabled. Otherwise, iiop-endpoint.xml.
Note: If using this option, env.IIOP_ENDPOINT_HOST environment variable should be set to the server's host. See IIOP endpoint configuration for more details.
