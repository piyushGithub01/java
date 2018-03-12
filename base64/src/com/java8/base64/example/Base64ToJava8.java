package com.java8.base64.example;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64ToJava8 {

	public static void main(String[] args) {
		final String text = "Base64 finally in Java 8!";

		final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);

		final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
		System.out.println(decoded);
	

	// There are also URL-friendly encoder/decoder and MIME-friendly
	// encoder/decoder provided by the Base64 class (Base64.getUrlEncoder() /
	// Base64.getUrlDecoder(), Base64.getMimeEncoder() /
	// Base64.getMimeDecoder()).

	// When you have some binary data that you want to ship across a network,
	// you generally don’t do it by just converting data into stream of bits and
	// bytes over the network in a raw format. Why? because some media are
	// designed for streaming text only. These protocols may interpret your
	// binary data as control characters which they are not.

	// The encoded string with above characters is safe to be transferred over
	// network supporting text data without fear of losing data in confusion of
	// control characters.

	// Base 64 encoding convert your binary data into 64 printable ASCII
	// characters. Generally it is done for binary data in email messages and
	// "basic" HTTP authentication. These 64 printable characters are:

	// 26 uppercase letters [A…Z]
	// 26 lowercase letters [a…z]
	// 10 digits [0…9]
	// 2 symbols

	// Wrap to a base 64 encoded output stream

	// If you don’t want to directly work with data and rather prefer to work
	// with streams, you can wrap the output stream such that all data written
	// to this output stream will be automatically base 64 encoded.
	
	Path originalPath = Paths.get("c:/temp", "mail.txt");
	Path targetPath = Paths.get("c:/temp", "encoded.txt");
	Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
	try(OutputStream output = Files.newOutputStream(targetPath)){
	    //Copy the encoded file content to target file
	    Files.copy(originalPath, mimeEncoder.wrap(output));
	    //Or simply use the encoded output stream
	    OutputStream encodedStrem = mimeEncoder.wrap(output);
	}catch(Exception ex){}
	}
	
}

