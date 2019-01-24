package com.kiran.azure.storage.azureblob.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestController
public class AzureBlobController {

   @Value("blob://test/myfile.txt")
   private Resource blobFile;

   @GetMapping(value = "/")
   public String readBlobFile() throws IOException {
      return StreamUtils.copyToString(
         this.blobFile.getInputStream(),
         Charset.defaultCharset()) + "\n";
   }

   @PostMapping(value = "/")
   public String writeBlobFile(@RequestBody String data) throws IOException {
      try (OutputStream os = ((WritableResource) this.blobFile).getOutputStream()) {
         os.write(data.getBytes());
      }
      return "File was updated.\n";
   }
}