resolvers in ThisBuild += "lightbend-commercial-mvn" at "https://repo.lightbend.com/pass/shutQaw3_VIxQDoW9fjY--WekK55j4NCukQwLYLmB27qib4E/commercial-releases"
resolvers in ThisBuild += Resolver.url(
  "lightbend-commercial-ivy",
  url(
    "https://repo.lightbend.com/pass/shutQaw3_VIxQDoW9fjY--WekK55j4NCukQwLYLmB27qib4E/commercial-releases"
  )
)(Resolver.ivyStylePatterns)
