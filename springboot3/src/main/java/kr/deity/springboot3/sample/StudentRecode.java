package kr.deity.springboot3.sample;

public record StudentRecode (
    String firstName,
    String lastName,
    Long studentId,
    String email,
    String phoneNumber,
    String address,
    String country,
    int age) {
}
