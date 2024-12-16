# Koma

## Architecture

Compile: file -> parse language -> assign template -> post assign -> output

## Layout

```text
/static-site
|-- /content
|   |-- /posts                  
|   |-- /pages                   
|   |-- /drafts                   
|-- /static
|   |-- /css                      
|   |-- /js                       
|   |-- /images                   
|-- /templates                    
|   |-- post.html.template        
|   |-- page.html.template        
|   |-- layout.html.template      
|-- /output                       
|   |-- /index.html               
|   |-- /blog
|   |   |-- post-1.html           
|   |   |-- post-2.html           
|   |   |-- post-3.html           
|   |-- /about.html               
|   |-- /contact.html             
|-- /config.yml                # config file
```

## TODO
 - [ ] process link
 - [ ] theme system