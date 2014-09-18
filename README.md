Java EE 7: Payload marshalling in RESTful web services using JAX-RS 2.0
===========================

Consider a scenario where we already have rich domain Java objects available to us. But

* We do not have access to the source code
* We cannot really annotate them with JAXB annotations
* The domain entities are legacy POJOs which are not JAXB compatible

Here is where we can leverage customized content handling feature available in JAX-RS 2.0. The MessageBodyWriter and MessageBodyReader interfaces provide us a way to plug in our customized marshalling/unmarshalling mechanism and allow the JAX-RS run time to take care of the rest!

The corresponding blog post can be read from http://abhirockzz.wordpress.com/2014/03/27/jax-rs-2-0-custom-content-handling/
